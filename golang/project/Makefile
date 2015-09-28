TOPDIR=$(PWD)
GOPATH=$(TOPDIR)

all: install

install: src
	GOPATH=$(GOPATH) go install -v hello

src:
	GOPATH=$(GOPATH) go get -v hello

clean:
	rm -rf bin pkg *~ src/github.com

.PHONY: src
