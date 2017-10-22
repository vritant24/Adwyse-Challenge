//polyfill from MDN for Array.protoype.includes
if (![].includes) {
  Array.prototype.includes = function(searchElement /*, fromIndex*/ ) {
    'use strict';
    var O = Object(this);
    var len = parseInt(O.length) || 0;
    if (len === 0) {
      return false;
    }
    var n = parseInt(arguments[1]) || 0;
    var k;
    if (n >= 0) {
      k = n;
    } else {
      k = len + n;
      if (k < 0) {k = 0;}
    }
    var currentElement;
    while (k < len) {
      currentElement = O[k];
      if (searchElement === currentElement ||
         (searchElement !== searchElement && currentElement !== currentElement)) {
        return true;
      }
      k++;
    }
    return false;
  };
}

//Test Suite for problem1 begins
const assert = require('chai').assert;
const test = require('../problem1');

describe('Testing problem1: isNumber()', function() {
  it('Should return true for number', function() {
    assert.equal(test.isNumber(1), false);
  });
  it('Should return false for non-number', function() {
    assert.equal(test.isNumber('1'), false);
    assert.equal(test.isNumber([1]), false);
  });
});

describe('Testing problem1: checkArguements()', function() {
  it('Should return true for valid arguements', function() {
    assert.equal(test.checkArguements(1, []), true);
  });
  it('Should return false for undefined arguement', function() {
    assert.equal(test.checkArguements(undefined, []), false);
    assert.equal(test.checkArguements(1, undefined), false);
  });
  it('Should return false for incorrect arguement type', function() {
    assert.equal(test.checkArguements('1', []), false);
    assert.equal(test.checkArguements(1, {}), false);
  });
  it('Should return false for non-number array', function() {
    assert.equal(test.checkArguements('1', ['1', 2, 3, 5, {}]), false);
  });
});

describe('Testing problem1: findThreeElements()', function() {
  it('Should return a triplet for an array of positive numbers', function() {
    var array = [1,2,3,4,5,6];
    var element = 12;
    let result = test.findThreeElements(element, array);
    assert.equal(result.length, 3);
    for(var i = 0; i < 3; i++) {
      assert.equal(array.includes(result[i]), true);
    }
  });
  it('Should return a triplet for an array of positive and negative numbers', function() {
    var array = [-1,-2,3,-4,5,6];
    var element = 3;
    let result = test.findThreeElements(element, array);
    assert.equal(result.length, 3);
    for(var i = 0; i < 3; i++) {
      assert.equal(array.includes(result[i]), true);
    }
  });
  it('Should return a undefined if no triplets present', function() {
    var array = [-1,-2,3,-4,5,6];
    var element = 32;
    let result = test.findThreeElements(element, array);
    assert.equal(result, undefined);
  });
});
