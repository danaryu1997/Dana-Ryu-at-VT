var Vaccine = artifacts.require("./Vaccine.sol"); // Truffle creats artifacts

module.exports = function(deployer) {
  deployer.deploy(Vaccine);
};
