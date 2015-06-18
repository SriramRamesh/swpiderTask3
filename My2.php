<html>
   <head>
      <title>Online PHP Script Execution</title>
   </head>
	
   <body>
      <?php
	//function keyword is used to define a function
	function make_seed()
	{
	  //$ symbol is given to all variables to differentiate them from keywords and other functions
	  //list() is used to assign custom key to arrays and explode is used to divide a string into substring using a space " " or any other character
	  list($usec, $sec) = explode(' ', microtime());
	  return (float) $sec + ((float) $usec * 100000);
	}
	//srand is used to set the seed for the random numbers similar to C++
	//rand() returns the random no
	//mt_srand() and mt_rand() can be used for better random numbers instead of srand() and rand()
	srand(make_seed());
	$randval = rand();
	//echo is like cout<<
	echo $randval;
      ?>
   </body>
	
</html>