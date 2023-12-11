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
func getMaxColours(line string) (red int,green int,blue int){
	eachSet:= strings.Split(line, ";")
	for _,eachRound := range eachSet {
		colors := strings.Split(eachRound, ",")
		for _,val := range colors {
				colors := strings.Split(val," ")
				tempColor,_:=strconv.Atoi(colors[1])
			if(strings.Contains(val,"red")){
				if(tempColor >red){
					
					red=tempColor 
				}
				
			}else if(strings.Contains(val,"green")) {
				if(tempColor >green){
					
					green=tempColor 
				}
				
			}else if(strings.Contains(val,"blue")) {
				if(tempColor >blue){
					
					blue = tempColor 
				}
				
			}
		}
	}
	 return 
}

func main(){
	var passedGame []int
	var answer int
	var maxSum int
  input := readInput(input_file)
  for _,eachLine := range input {
	
	parts := strings.Split(eachLine, ":")
	game := strings.Split(parts[0], " ")
	gameNumber,_:= strconv.Atoi(game[1])
	
	passed := getAllColours(parts[1])
	
	if ( passed ){
		passedGame=append(passedGame,gameNumber)
		//log.Printf("line:%v \n red:%v green::%v blue::%v \n",eachLine,red,green,blue)
	}
	red,green,blue := getMaxColours(parts[1])
	maxSum +=(red*green*blue)
	log.Printf("line:%v \n red:%v green::%v blue::%v \n",eachLine,red,green,blue)
  }
  for _,val := range passedGame {
	answer+=val
  }
  log.Println(answer)
  log.Println(maxSum)
 
  
}