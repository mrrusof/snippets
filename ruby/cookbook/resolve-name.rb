#!/usr/bin/env ruby

require 'resolv'
Resolv::DNS.new.each_address('oreilly.com') { |addr| puts addr }
