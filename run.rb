# -*- coding: utf-8 -*-
# buffers は 10倍 していくと一晩放置しても終わらなかった

RunFile = "target/FileWrite-0.0.1-SNAPSHOT.jar"

outputfile = "test_file"
buffers    = [1, 100, 10000, 1000000]
file_sizes = [0, 100, 10000, 1000000, 100000000, 10000000000]
string     = "hoge"

buffers.each do |buffer|
  File.open("result/#{buffer}.txt","w") do |file|
    file_sizes.each do |size|
      puts "run : buffer #{buffer}, size #{size}"
      time = Time.now
      system("java -jar #{RunFile} -b #{buffer} -c #{string} -s #{size} -n #{outputfile}")
      diff =  Time.now - time
      system("rm #{outputfile}")
      file.puts "#{size} #{diff}"
    end
  end
end
