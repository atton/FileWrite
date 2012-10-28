# -*- coding: utf-8 -*-

RunFile = "target/FileWrite-0.0.1-SNAPSHOT.jar"

outputfile = "test_file"
buffers    = [1, 10, 100, 1000, 10000, 100000, 1000000]
file_sizes = [0, 100 , 10000, 1000000, 100000000, 10000000000]

buffers.each do |buffer|
  File.open("result/#{buffer}.txt","w") do |file|
    file_sizes.each do |size|
      time = Time.now
      system("java -jar #{RunFile} -b #{buffer} -c #{string} -s #{size} -n #{outputfile}")
      diff =  Time.now - time
      system("rm #{outputfile}")
      file.puts "#{size} #{diff}"
    end
  end
end
