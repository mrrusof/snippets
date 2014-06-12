#!/bin/bash

echo $1 >/tmp/pgsql-ver

sudo su
set -e

ws=0
declare -a ws_sy=( '\' '|' '/' '-' )
function wait_step {
    echo -ne '\b'; \
    sleep 0.2; \
    echo -n ${ws_sy[ws]}; \
    (( ws = (ws+1) % 4 )) || true
}

set -x
VER=`cat /tmp/pgsql-ver`
PGDATA=/usr/local/pgsql/data
HOME=/home/postgres
set +x

echo '**** Enabling yum caching'
sed -i s/keepcache=0/keepcache=1/ /etc/yum.conf

echo '**** Restoring yum cache'
if [ -d /vagrant/yum-cache-$VER ]; then
    cp -rv /vagrant/yum-cache-$VER/* /var/cache/yum
fi

echo '**** Installing up emacs'
yum -y install emacs


if [ "$VER" = "8.4" ]; then
    echo '**** Installing pgdg repo for Postgre 8.4.21'
    yum -y install http://yum.postgresql.org/8.4/redhat/rhel-6-x86_64/pgdg-centos-8.4-3.noarch.rpm

    echo '**** Installing PostgreSQL 8.4.21'
    yum -y install \
        postgresql-libs-8.4.20-1.el6_5.x86_64 \
        postgresql84-8.4.21-1PGDG.rhel6.x86_64 \
        postgresql84-devel-8.4.21-1PGDG.rhel6.x86_64 \
        postgresql84-server-8.4.21-1PGDG.rhel6.x86_64 \
        postgresql84-libs-8.4.21-1PGDG.rhel6.x86_64 \
        postgresql84-contrib-8.4.21-1PGDG.rhel6.x86_64
else
    echo '**** Installing pgdg repo for Postgre 9.3.4'
    yum -y install http://yum.postgresql.org/9.3/redhat/rhel-6-x86_64/pgdg-centos93-9.3-1.noarch.rpm

    echo '**** Installing PostgreSQL 9.3.4'
    yum -y install \
        postgresql93-libs-9.3.4-1PGDG.rhel6.x86_64 \
        postgresql93-plperl-9.3.4-1PGDG.rhel6.x86_64 \
        postgresql93-devel-9.3.4-1PGDG.rhel6.x86_64 \
        postgresql93-odbc-09.03.0300-1PGDG.rhel6.x86_64 \
        postgresql-libs-8.4.20-1.el6_5.x86_64 \
        postgresql93-server-9.3.4-1PGDG.rhel6.x86_64 \
        postgresql93-plpython-9.3.4-1PGDG.rhel6.x86_64 \
        postgresql93-debuginfo-9.3.4-1PGDG.rhel6.x86_64 \
        postgresql93-jdbc-debuginfo-9.3.1100-1PGDG.rhel6.x86_64 \
        postgresql93-test-9.3.4-1PGDG.rhel6.x86_64 \
        postgresql93-odbc-debuginfo-09.03.0300-1PGDG.rhel6.x86_64 \
        postgresql93-jdbc-9.3.1100-1PGDG.rhel6.x86_64 \
        postgresql93-contrib-9.3.4-1PGDG.rhel6.x86_64 \
        postgresql93-docs-9.3.4-1PGDG.rhel6.x86_64 \
        postgresql93-9.3.4-1PGDG.rhel6.x86_64 \
        postgresql93-pltcl-9.3.4-1PGDG.rhel6.x86_64
fi

echo '**** Saving yum cache'
rm -rfv /vagrant/yum-cache-$VER
cp -rv /var/cache/yum /vagrant/yum-cache-$VER

echo '**** Compiling locale es_MX.iso88591'
localedef -i es_MX -f ISO-8859-1 es_MX.iso88591

echo '**** Creating cluster'
mkdir -p $PGDATA
chown postgres:postgres $PGDATA
cd $PGDATA
sudo -u postgres /usr/pgsql-$VER/bin/initdb -D $PGDATA --encoding=LATIN1 --locale=es_MX.iso88591 --pgdata=$PGDATA --auth=ident

echo '**** Applying postgres.conf'
rm -f $PGDATA/postgresql.conf
cp /vagrant/postgresql.conf $PGDATA
chown postgres:postgres $PGDATA/postgresql.conf
if [ -d /vagrant/pgdata-$VER ]; then
    echo '**** Recreating database'
    rm -rf $PGDATA/*
    cp -r /vagrant/pgdata-$VER/* $PGDATA
    chown -R postgres:postgres $PGDATA
    chmod 0700 $PGDATA

    echo '**** Starting PostgreSQL'
    sudo -u postgres /usr/pgsql-$VER/bin/postgres -h '' -k $PGDATA -D $PGDATA 1>/tmp/pgsql.log < /dev/null 2>&1 &
    echo "**** PID is $!"
    echo $! > /tmp/postgres.pid
    ps -A u | grep postgres
    while ! sudo -u postgres psql -h $PGDATA -c 'SELECT current_timestamp' template1 >/dev/null 2>&1; do wait_step;  done
else
    echo '**** Starting PostgreSQL'
    sudo -u postgres /usr/pgsql-$VER/bin/postgres -h '' -k $PGDATA -D $PGDATA 1>/tmp/pgsql.log < /dev/null 2>&1 &
    echo "**** PID is $!"
    echo $! > /tmp/postgres.pid
    ps -A u | grep postgres
    while ! sudo -u postgres psql -h $PGDATA -c 'SELECT current_timestamp' template1 >/dev/null 2>&1; do wait_step;  done
    
    echo '**** Creating database'
    set -x
    sudo -u postgres /usr/pgsql-$VER/bin/createdb --encoding=LATIN1 -h $PGDATA tinydb
    set +x
    sudo -u postgres psql -h $PGDATA -c 'CREATE TABLE tinytable (name varchar(50), age int)' tinydb
    sudo -u postgres psql -h $PGDATA -c "INSERT INTO tinytable VALUES ('Ruslan', 28)" tinydb
    sudo -u postgres psql -h $PGDATA -c 'SELECT * FROM tinytable' tinydb

    echo '**** Creating plpgsql language'
    if [ "$VER" = "8.4" ]; then
        sudo -u postgres psql -h $PGDATA tinydb -c "CREATE LANGUAGE plpgsql;" >/dev/null 2>&1
    fi

    echo '**** Stopping PostgreSQL'
    kill `cat /tmp/postgres.pid`

    echo '**** Caching database'
    cp -r $PGDATA /vagrant/pgdata-$VER
fi
