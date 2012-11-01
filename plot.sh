#!/bin/sh

file_name="result/graph"

gnuplot <<-EOF
set terminal postscript eps
set output "${file_name}.eps"
set xlabel "file size (log scale)"
set logscale x
set ylabel "time (log scale)"
set logscale y
set title "file generate times to change buffer size"
plot "result/1.txt" with lines, "result/100.txt" with lines, "result/10000.txt" with lines, "result/1000000.txt" with lines
EOF

epstopdf ${file_name}.eps
rm ${file_name}.eps
