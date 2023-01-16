pragma solidity 0.8.15;

contract ExampleString {
    string public myString = "Hello World";
    bytes public myBytes = "Hello World";
    
    #ephemeral data location
    function setMyString(string memory _myString) public {
        myString = _myString;
    }

    function compareTwoStrings(string memory _myString) public view return(bool) {
        return keccak256(abi.encodePacked(myString)) == keccak256(abi.encodePacked(_myString));
    }
}
