run: main.exe
	./main.exe

main.exe: main.pl
	swipl --goal=main --stand_alone=true -o main.exe -c main.pl

clean:
	rm -rf main.exe

.PHONY: run clean