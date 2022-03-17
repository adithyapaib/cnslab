BEGIN{
 count = 0;
 }
 {
 if($1=="d"){
   count++;}
}
END{
  printf("%d is the number of packets dropped \n",count);
 }
