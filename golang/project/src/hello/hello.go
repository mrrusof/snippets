package main

import (
	"fmt"
	"github.com/kr/fs"
)

func dependency_demo() {
	walker := fs.Walk("/")
	fmt.Printf("walker.Path() = %s\n", walker.Path())
}

func channels_demo() {
	worklist := make(chan string)

	// Consume worklist
	go func() {
		for work := range worklist {
			if(work == "\000") { break }
			fmt.Printf(work)
		}
		close(worklist)
	}()

	// Dispatch work
	worklist <- "This "
	worklist <- "is "
	worklist <- "a "
	worklist <- "new "
	worklist <- "day"
	worklist <- "!\n"
	worklist <- "\000"
}

func Append(slice1, slice2 []int) []int {
	slice := slice1
	l1 := len(slice1)
	l2 := len(slice2)
	if l1 + l2 > cap(slice) {
		slice = make([]int, l1+l2)
		for i, c := range slice1 {
			slice[i] = c
		}
	}
	slice = slice[0:l1+l2]
	fmt.Printf("len(slice) = %d; cap(slice) = %d\n", len(slice), cap(slice))
	for i, c := range slice2 {
		slice[l1+i] = c
	}
	return slice
}

func slices_demo() {
	data1 := []int{ 1, 2, 3, 4, 5 }
	data2 := []int{ 6, 7, 8, 9, 0 }

	fmt.Printf("data1 = %v; len(data1) = %d; cap(data1) = %d\n", data1, len(data1), cap(data1))
	fmt.Printf("data2 = %v; len(data2) = %d; cap(data2) = %d\n", data2, len(data2), cap(data2))

	fmt.Printf("Append(data1, data2) = %v\n", Append(data1, data2))
}

func main() {
	dependency_demo()
	channels_demo()
	slices_demo()
	fmt.Printf("End of Demo\n")
}
