#!/usr/bin/env python

import sys
import imaplib
import email

# 1. Fetch most recent email from imap.gmail.com
M = imaplib.IMAP4_SSL('imap.gmail.com', 993)
addr = "ruslanledesmagarza@gmail.com"
pwd = raw_input("Password for "+addr+": ")
try:
    M.login(addr, pwd)
except imaplib.IMAP4.error:
    print "FAILURE: could not login to", addr
    sys.exit(1)
rv, _ = M.select("Inbox")
if rv != "OK":
    print "FAILURE: could not select Inbox"
    sys.exit(2)
rv, msgnums = M.search(None, "ALL")
if rv != "OK":
    print "FAILURE: no messages found in Inbox (1)"
    sys.exit(3)
if len(msgnums) == 0:
    print "FAILURE: no messages found in Inbox (2)"
    sys.exit(4)
msgnums = msgnums[0].split()
if len(msgnums) == 0:
    print "FAILURE: no messages found in Inbox (3)"
    sys.exit(5)
rv, rawmsg = M.fetch(msgnums[-1], "(RFC822)")
if rv != "OK":
    print "FAILURE: could not get message", msgnums[-1]
    sys.exit(6)
msg = email.message_from_string(rawmsg[0][1])
print
print "Msg Number:", msgnums[-1]
print "Subject:   ", msg["Subject"]
print "Date:      ", msg["Date"]
if not msg.is_multipart():
    print "Content-type:",msg.get_content_type()
    print
    print msg.get_payload()
else:
    for part in msg.walk():
        print "--------------------------------------------------"
        print "Conten-type:",part.get_content_type()
        print
        print part.get_payload()
