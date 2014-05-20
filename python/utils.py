import sys

def failwith(str):
    print 'FAILURE:',str
    sys.exit(1)

def warn(str):
    print 'WARNING:', str
