const Token = artifacts.require("Token");
const EthSwap = artifacts.require("EthSwap");

module.exports = async function(deployer) {
  deployer.deploy(Token);
  const token = await Token.deployed()
  
  deployer.deploy(EthSwap);
  const ethSwap = await EthSwap.deployed()

  // Transfer all tokens to Ethswap
  await token.transfer(ethSwap.address, '1000000000000000000000000')
};