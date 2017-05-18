problem1 = {}; //to be exported for tests

/**
  *isNumber()
  *
  *returns true if element in an array is of type Number
  **/
problem1.isNumber = function (element, index, array) {
  return typeof element === typeof 1;
};

/**
  *checkArguements()
  *
  *returns true if the arguements passed to problemOne()
  *are valid
  */
problem1.checkArguements = function(element, array) {
  //check if element is undefined or null
  if(!element) {
    // console.log("element is undefined/null");
    return false;
  }

  //check if array is undefined or null
  if(!array) {
    // console.log("array is undefined/null");
    return false;
  }

  //check if element if of type Number
  if(typeof element !== typeof 1) {
    // console.log("given element is not of type number");
    return false;
  }

  //check if array if of type [object Array]
  if(Object.prototype.toString.call(array) !== '[object Array]') {
    // console.log("given array is not of type array");
    return false;
  }

  //check if all elements in the array are of type Number
  if(!array.every(problem1.isNumber)) {
    // console.log("given array contains non-numbers");
    return false;
  }

  return true;
}

/**
  *findThreeElements()
  *
  *prints three elements in an array whose sum equals to
  *the given element
  *
  **/
problem1.findThreeElements = function(element, array) {

  //check if there are more than 2 elements in the array
  if(array.length < 3) {
    return;
  }

  //sort the array
  array.sort();

  var fixed,  //element of array fixed for each loop
      left,   //element in array after fixed element
      right,  //element in array at the end of the array
      sum;    //sum of elements in array at indices fixed, left and right

  for(fixed = 0; fixed < array.length - 2; fixed++) {
    left = fixed + 1;
    right = array.length - 1;

    //find sum of all cominations with fixed element
    while(left < right) {
      sum = array[fixed] + array[left] + array[right];
      if(sum === element) {
        //three elements found
        return [array[fixed], array[left], array[right]];
      }
      else if (sum < element) {
        //the sum is lesser than the element, that would mean that
        //the smaller element in the sum needs to be larger
        left++;
      }
      else {
        //the sum is greater than the element, that would mean that
        //the larger element in the sum needs to be smaller
        right--;
      }
    }
  }
  return;
};

/**
  * Main function that solves problem1
  *
  * Console Logs the result from calculations
  */
problem1.main = function(element, array) {

  //check if the arguements are valid
  if(!problem1.checkArguements(element, array)) {
    return;
  }

  //if arguements are good, find the three elements
  var triplet = problem1.findThreeElements(element, array);
  if(triplet) {
    console.log("Three elements found: " + triplet[0] + " " + triplet[1] + " " + triplet[2] + "\n");
  } else {
    console.log("Three elements not found");
  }
};

module.exports = problem1;
