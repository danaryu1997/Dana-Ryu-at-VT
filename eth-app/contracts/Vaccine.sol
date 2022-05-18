pragma solidity ^0.5.0;

contract Vaccine {
  uint public personCount = 0; //state variable, scope of variable belongs to smart contract


  struct People {
    uint id;
    string name;
    string vtype;
  }

  //access the storage in blockchain
  mapping(uint => People) public record;

  constructor() public {
    createPeople("Dr. Blockchain", "1. Pfizer, 2. Pfizer, 3. Pfizer");
  }

  function createPeople(string memory _name, string memory _vtype) public {
    personCount++;
    record[personCount] = People(personCount, _name, _vtype);
    emit RecordCreated(personCount, _name, _vtype);
  }

  event RecordCreated(
    uint id,
    string name,
    string vtype
  );



}
