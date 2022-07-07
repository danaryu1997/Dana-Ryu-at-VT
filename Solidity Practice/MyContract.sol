pragma solidity ^0.6.0;

contract MyContract {
    // State variables
    int public myInt = 1;
    uint public myUint = 1; //cant have negative , default = uint256
    uint256 public myUint256 = 1; // need to allocate more space than uint8
    uint8 public myUint8 = 1;

    string public myString = "Hello, World!";
    bytes32 public myBytes = "Hello, World!"; // performaces differs

    address public myAddress = 0xE42Fa6e32168904Eb1d85a71E6c36E6600A84438;


    struct MyStruct {
        uint id;
        string name;
    }

    MyStruct public myStruct = MyStruct(1, "Dana");

    // Local variables
    function getValue() public pure returns(uint) {
        uint value = 1;
        return value;
    }

}
