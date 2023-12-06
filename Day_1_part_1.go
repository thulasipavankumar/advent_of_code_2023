package main

import (
	"bufio"
	"log"
	"os"
)

const input = "day_1_part_1_input.txt"

func main() {
	var arr []string
	f, err := os.OpenFile(input, os.O_RDONLY, os.ModePerm)
	if err != nil {
		log.Fatalf("open file error: %v", err)
		return
	}
	defer f.Close()

	sc := bufio.NewScanner(f)
	for sc.Scan() {
		line := sc.Text()
		arr = append(arr, line) // GET the line string
		//	log.Println(line)
	}
	if err := sc.Err(); err != nil {
		log.Fatalf("scan file error: %v", err)
		return
	}
	var sum int

	for i, v := range arr {

		front, back := -1, -1
		var hit bool
		for _, ch := range v {

			if ch >= 48 && ch <= 57 {
				//log.Printf("hit in line %d %v ",i,string(ch))

				if hit {
					back = int(ch) - 48
				} else {
					front = int(ch) - 48
					hit = true
				}
			}

		}
		if back == -1 {
			back = front
		}
		sum = sum + back + (front * 10)
		log.Printf("hit in line %d %d %d \n ", i, front, back)

	}
	log.Println("sum", sum)
}
