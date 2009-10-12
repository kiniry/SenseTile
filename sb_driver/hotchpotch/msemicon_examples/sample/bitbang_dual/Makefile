
include ../Rules.make

APP = bitbang_dual

all: $(APP)

$(APP): main.c	
	$(CC) -o $(APP) $(CFLAGS) main.c	

clean:
	rm -f *.o ; rm $(APP)
