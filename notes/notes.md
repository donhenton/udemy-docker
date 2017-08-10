## Docker Files
RUN lines each commit a write layer of an image

so 
RUN a && b && c

is preferred to 
RUN a
RUN b
RUN c

also if task a loads env variables via bash then those variables last for all the
other activities b and c, when they are chained, as the RUN command creates 
a separate instance of bash