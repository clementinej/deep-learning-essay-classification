require 'ffi/aspell'

speller = FFI::Aspell::Speller.new('en_US')

sentences = File.open("essay.txt", "r")
output = "output.txt"
file = File.open(output, "w")


while(s = sentences.gets)
	s.split(' ').each do |word|
		if !speller.correct?(word)
			#correct spelling
 			word = speller.suggestions(word)[0] 		
		end
		#write word to file
		file.print word 
		file.print ' '
	end
	#end of sentence
	file.puts '.'
end

file.close


