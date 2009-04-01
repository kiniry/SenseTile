
include ../Rules.make

APP = large_read

all: $(APP)

$(APP): main.c	
	$(CC) -o $(APP) $(CFLAGS) main.c	

clean:
	rm -f *.o ; rm $(APP)
