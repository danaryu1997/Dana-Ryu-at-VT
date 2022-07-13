//const { assert } = require('chai')

const Token = artifacts.require('Token')
const EthSwap = artifacts.require('EthSwap')


require('chai').use(require('chai-as-promised')).should()


contract('EthSwap', (accounts) => {

    before(async () => {
        let token = await Token.new()
        let ethSwap = await EthSqap.new()

        // Transfer all tokens to Ethswap
        await token.transfer(ethSwap.address, tokens('1000000'))

    })
    describe('EthSwap deployment', async() => {
        it('contract has a name', async() => {
            let ethSwap = await EthSwap.new()
            const name = await ethSwap.name()
            assert.equal(name, 'EthSwap Instant Exchage')
        })
    })

    describe('EthSwap deployment', async() => {
        it('contract has a name', async() => {
            let token = await Token.new()
            const name = await token.name()
            assert.equal(name, 'DApp Token')
        })
    })

    it('contract has tokens', async() => {
        let balance = await token.balanceOf(ethSwap.address)
        assert.equal(balance.toString(), '1000000000000000000000000')
    })





})