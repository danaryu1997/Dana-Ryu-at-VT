pragma solidity ^0.6.0;

contract Counter {
    uint count;

    constructor() public {
        count = 0;
    }

    function getCount() public view returns(uint) {
        return count;
    }

    function incrementCount() public {
        count = count + 1;
    }


    /** Same as:
    uint public count = 1;
    
    function incrementCount() public {
        count ++;
    }
    */
    


}