#!/usr/bin/env irb

ceiling = 50
[1, 10, 49, 50.1, 200].select { |x| x < ceiling }
