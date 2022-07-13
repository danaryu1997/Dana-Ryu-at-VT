pragma solidity ^0.6.0;

contract MyMapping {
    //Mappings
    //mapping(key => value) public myMap;
    mapping(uint => string) public names;
    mapping(uint => Book) public books;
    
    //Nested Mapping
    //mapping(key=> mapping(key2 => value2)) public myMapping;
    mapping(address=> mapping(uint => Book)) public myBooks;
     

    struct Book {
        string title;
        string author;
    }


    constructor() public {
        names[1] = "Adam";
        names[2] = "Carl";
        names[3] = "Dana";
    }

    function addBook(uint _id, string memory _title, string memory _author) public {
        books[_id] = Book(_title, _author);
    }

    function addMyBook(uint _id, string memory _title, string memory _author) public {
        myBooks[msg.sender][_id] = Book(_title, _author);
    }


}
