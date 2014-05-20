#!/usr/bin/env python

import sys
import imaplib
import email
import log

# 1. Fetch most recent email from imap.gmail.com
M = imaplib.IMAP4_SSL('imap.gmail.com', 993)
addr = "ruslanledesmagarza@gmail.com"
pwd = raw_input('Password for %s: ' % addr)
log.event('looking for new messages')
try:
    M.login(addr, pwd)
except imaplib.IMAP4.error:
    log.failwith('could not login to %s' % addr)
rv, _ = M.select('Inbox')
if rv != 'OK':
    log.failwith('could not select Inbox')
rv, msgnums = M.search(None, 'ALL')
if rv != 'OK':
    log.failwith('search for new messages failed')
if len(msgnums) == 0:
    log.failwith('search for new messages returned no messages')
msgnums = msgnums[0].split()
if len(msgnums) == 0:
    log.event('there are no new messages')
    sys.exit(0)
rv, rawmsg = M.fetch(msgnums[-1], '(RFC822)')
if rv != 'OK':
    log.failwith('could not get message %s' % msgnums[-1])
msg = email.message_from_string(rawmsg[0][1])
print
print 'Msg Number:', msgnums[-1]
print 'Subject:   ', msg['Subject']
print 'Date:      ', msg['Date']
if not msg.is_multipart():
    print 'Content-type:',msg.get_content_type()
    print
    print msg.get_payload()
else:
    for part in msg.walk():
        print '--------------------------------------------------'
        print 'Conten-type:',part.get_content_type()
        print
        print part.get_payload()
