#!/usr/bin/env irb

a1 = []
a2 = [1,2,3]
a3 = [1,2,3,'a','b','c',nil]

a4 = %w{1 2 3}

a5 = [1, 2, 3]
a5 << 4.0
a5 << "five"
a5.size

[1,2,3,4,5].each { |x| puts x }                # iter
[1,2,3,4,5].each do |x| puts x end             # iter
[1,2,3,4].collect { |x| x ** 2 }               # map
[1,2,3,4].collect do |x| x ** 2 end            # map
['a','b','c'].each_with_index do |item, index| # iteri
  puts "At position #{index}: #{item}"
end
['a','b','c'].each_with_index do |item, index|
  puts "At position #{index}: #{item}"
end
