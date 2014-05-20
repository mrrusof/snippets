all: copy_struct.exe

copy_struct.exe: copy_struct.c
	gcc -o copy_struct.exe copy_struct.c

profile: copy_struct.exe
	valgrind --tool=massif --massif-out-file=massif.out --time-unit=B ./copy_struct.exe
	ms_print massif.out

clean:
	rm -f copy_struct.exe massif.out
