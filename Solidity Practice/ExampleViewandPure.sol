pragma solidity 0.8.15;

contract ExampleViewandPure {

    uint public myStorageVariable;

    #View Function
    function getMyStorageVariable() public view returns(uint) {
        return myStorageVariable;
    }

    #Pure Function
    function getAddition(uint a, uint b) public pure returns(uint) {
        return a+b;
    }

    function setMyStorageVariable(uint _newVar) public returns(uint) {
        myStorageVariable = _newVar;
        return _newVar;
    }
}
