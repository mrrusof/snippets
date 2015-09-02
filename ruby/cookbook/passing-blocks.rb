#!/usr/bin/env irb

# a_block = { |x| puts x }
a_block = lambda { |x| puts x }

def identity(&l)
  l
end

b = identity { puts "hola" }
b.call
