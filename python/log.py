import time
import utils

log = False

def start_log():
    global log
    log = True

def event(str):
    if log: print time.strftime('%Y.%M.%d %H:%M:%S')+' : '+str

def failwith(str):
    if log:
        msg = '%s : %s' % (time.strftime('%Y.%M.%d %H:%M:%S'), str)
        print msg
        utils.failwith(msg)

def warn(str):
    if log: event('WARNING: %s' % str)
