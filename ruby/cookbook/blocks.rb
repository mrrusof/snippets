#!/usr/bin/env irb

1.upto 2 do |x| puts x end
# 1.upto(3) { |x| puts x }
hello = lambda { "Hello" }
hello.call

log = lambda { |str| puts "[LOG] #{str}" }
log.call("This is a log message.")

log = ->(str) { puts "[LOG] #{str}" }
log.call("This is another log message.")

{1=>2, 3=>4}.each { |k,v| puts "Key #{k}, value #{v}." }

def times_n(n)
  lambda { |x| x * n }
end

times_ten = times_n(10)
times_ten.call(5)

times_two = times_n(2)
times_ten.call(2)

circumference = times_n(2*Math::PI);
circumference.call(10);

[1,2,3].collect(&circumference)
