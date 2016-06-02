#!/usr/bin/env ruby

require 'open-uri'
require 'open_uri_redirections'
require 'nokogiri'
require 'set'

PREFIX_REGEX = /^http(s){0,1}:\/\/(www.){0,1}9gag.com.*/

class Set
  def choose
    e = self.to_a.first
    self.delete e
    return e
  end
end

def explore(current, discovered, work)
  raw = open(current, :allow_redirections => :all)
  tree = Nokogiri::XML(raw)
  tree.xpath('//a').each do |l|
    url = l['href']
    if url =~ PREFIX_REGEX and not discovered.member? url
      puts "--- Discover #{url}"
      discovered << url
      work << url
    end
  end
end

def main
  current = 'http://www.9gag.com/'
  discovered = Set.new([current])
  work = Set.new
  explore current, discovered, work
  while not work.empty?
    current = work.choose
    puts "*** Explore #{current}"
    explore current, discovered, work
  end
  puts 'Discovered URLs'
  discovered.each do |url|
    puts url
  end
end

main
