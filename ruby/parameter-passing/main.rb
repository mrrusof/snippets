#!/usr/bin/env ruby

def append_list(b)
  b << 1
end

def remove_list(c)
  c.delete(1)
end

a = ['a', 2]
puts "a = #{a}"
append_list(a)
puts "a = #{a}"
remove_list(a)
puts "a = #{a}"

def extend_map(a)
  a.merge!({ :b => 1 })
end

def remove_map(a)
  a.delete(:b)
end

h = { :a => 2 }
puts "h = #{h}"
extend_map(h)
puts "h = #{h}"
remove_map(h)
puts "h = #{h}"

def incr(a)
  a = a + 1
end

x = 1
puts "x = #{x}"
incr(x)
puts "x = #{x}"
