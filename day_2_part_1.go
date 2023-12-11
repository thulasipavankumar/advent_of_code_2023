package main
import (
	"bufio"
	"log"
	"os"
	"strings"
	"strconv"
)

const input_file = "day_2_part_1.txt"
func readInput(fileName string) (arr []string){
	f, err := os.OpenFile(fileName, os.O_RDONLY, os.ModePerm)
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
	return 
}
func getAllColours(line string) (passed bool){
	passed = false
	eachSet:= strings.Split(line, ";")
	for _,eachRound := range eachSet {
		colors := strings.Split(eachRound, ",")
		for _,val := range colors {
				colors := strings.Split(val," ")
				tempColor,_:=strconv.Atoi(colors[1])
			if(strings.Contains(val,"red")){
				if(tempColor >12){
					return 
				}
				
			}else if(strings.Contains(val,"green")) {
				if(tempColor >13){	
					return 
				}
				
			}else if(strings.Contains(val,"blue")) {
				if(tempColor >14){
					return 
				}
			
			}
		}
	}
	passed=true
	 return 
}

func main(){
	var passedGame []int
	var answer int
  input := readInput(input_file)
  for _,eachLine := range input {
	
	parts := strings.Split(eachLine, ":")
	game := strings.Split(parts[0], " ")
	gameNumber,_:= strconv.Atoi(game[1])
	
	if ( getAllColours(parts[1])  ){
		passedGame=append(passedGame,gameNumber)
		
	}
  }
  for _,val := range passedGame {
	answer+=val
  }
  log.Println(answer)
 
  
}