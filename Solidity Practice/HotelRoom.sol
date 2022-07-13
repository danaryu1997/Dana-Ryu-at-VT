pragma solidity ^0.6.0;

contract HotelRoom {

    
    
    //Visability
    //Events

    //Enums
    //Collection of option
    enum Status { Vacant, Occupied }
    Status currentStat;

    address payable public owner;

    //function that is going to be runed everytime the contract is created
    constructor() public {
        owner = msg.sender;
        currentStat = Status.Vacant;
    }

    event Occupy(address _occupant, uint _value);

    //Modifiers
    modifier onlyWhileVacant {
        //Check status
        require(currentStat == Status.Vacant, "Currently Occupied");
        _;
    }

    modifier costs(uint _amount) {
        require(msg.value >= _amount, "Not enough Ether provided");
        _;
    }


    //Ether - pay smart contracts
    receive() external payable onlyWhileVacant costs(2 ether) {
        currentStat = Status.Occupied;
        //Transfer the Eth payment to the owner
        owner.transfer(msg.value);
        emit Occupy(msg.sender, msg.value);
    }

    
}
