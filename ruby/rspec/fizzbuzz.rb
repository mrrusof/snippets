class FizzBuzz
  def fizzbuzzit(n)
    r = ''
    if n % 3 == 0
      r = 'fizz'
    end
    if n % 5 == 0
      r = r + 'buzz'
    end
    if r == ''
      return n
    end
    return r
  end
end

describe 'FizzBuzz' do
  it 'for 1 return 1' do
    fizzbuzz = FizzBuzz.new
    r = fizzbuzz.fizzbuzzit(1)
    expect(r).to eq(1)
  end
  it 'for 3 return fizz' do
    fizzbuzz = FizzBuzz.new
    r = fizzbuzz.fizzbuzzit(3)
    expect(r).to eq('fizz')
  end
  it 'for 5 return buzz' do
    fizzbuzz = FizzBuzz.new
    r = fizzbuzz.fizzbuzzit(5)
    expect(r).to eq('buzz')
  end
  it 'for 15 return fizzbuzz' do
    fizzbuzz = FizzBuzz.new
    r = fizzbuzz.fizzbuzzit(15)
    expect(r).to eq('fizzbuzz')
  end
  it 'for 16 return 16' do
    fizzbuzz = FizzBuzz.new
    r = fizzbuzz.fizzbuzzit(16)
    expect(r).to eq(16)
  end
end
