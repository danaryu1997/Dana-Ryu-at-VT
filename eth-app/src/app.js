//require('@metamask/legacy-web3')
//const Web3 = require('web3')
const { web3 } = window
const selectedAddress = web3.eth.defaultAccount
App = {
  loading: false,
  contracts: {},

  load: async () => {
    await App.loadWeb3()
    await App.loadAccount()
    await App.loadContract()
    await App.render()
    web3.eth.defaultAccount = web3.eth.accounts[0]
  },

  //connect browser to the blockchain
  //web 3 library
  //Metamesk - connect with client side app
  // https://medium.com/metamask/https-medium-com-metamask-breaking-change-injecting-web3-7722797916a8
  loadWeb3: async () => {
    if (typeof window.ethereum !== 'undefined') {
      App.web3Provider = window.ethereum
     // const web3 = new Web3()
    } else {
      window.alert("Please connect to Metamask.")
    }
    // Modern dapp browsers...
    if (window.ethereum) {
      window.web3 = new Web3(ethereum)
      try {
        // Request account access if needed
        await ethereum.enable()
        // Acccounts now exposed
        web3.eth.sendTransaction({/* ... */})
      } catch (error) {
        // User denied account access...
      }
    }
    // Legacy dapp browsers...
    else if (window.web3) {
      App.web3Provider = web3.currentProvider
      window.web3 = new Web3(web3.currentProvider)
      // Acccounts always exposed
      web3.eth.sendTransaction({/* ... */})
    }
    // // Modern dapp browsers...
    // if (window.ethereum) {
    //   App.web3Provider = window.ethereum;
    //   try {
    //     // Request account access
    //     const accounts = await window.ethereum.request('eth_requestAccounts');
    //     ethereum.send('eth_sendTransaction', { from: accounts[0], /* ... */ })
      
    //   } catch (error) {
    //     // User denied account access...
    //     console.error("User denied account access")
    //   }
    // }
    // Legacy dapp browsers...
    else if (window.web3) {
      App.web3Provider = window.web3.currentProvider;
    }
    // Non-dapp browsers...
    else {
      console.log('Non-Ethereum browser detected. You should consider trying MetaMask!')
    }
  },

  loadAccount: async () => {
    // Set the current blockchain account
    App.account = web3.eth.accounts[0]
  },

  loadContract: async () => {
    // Create a JavaScript version of the smart contract
    const vaccine = await $.getJSON('Vaccine.json')
    App.contracts.Vaccine = TruffleContract(vaccine)
    App.contracts.Vaccine.setProvider(App.web3Provider)

    // Hydrate the smart contract with values from the blockchain
    App.vaccine = await App.contracts.Vaccine.deployed()
  },

  render: async () => {
    // Prevent double render
    if (App.loading) {
      return
    }

    // Update app loading state
    App.setLoading(true)

    // Render Account
    $('#account').html(App.account)

    // Render Tasks
    await App.renderPeople()

    // Update loading state
    App.setLoading(false)
  },

  renderPeople: async () => {
    // Load the total task count from the blockchain
    const peopleCount = await App.vaccine.personCount()
    const $taskTemplate = $('.taskTemplate')

    // Render out each person with a new template
    for (var i = 1; i <= peopleCount; i++) {
      // Fetch the record data from the blockchain
      const record = await App.vaccine.record(i)
      const pplId = record[0].toNumber()
      const pplName = record[1]
      const pplVaccine = record[2]

      // Create the html for the task
      const $newTemplate = $taskTemplate.clone()
      $newTemplate.find('.nameContent').html(pplName)
      $newTemplate.find('.vaccineContent').html(pplVaccine)
      //$newTemplate.find('input')
      //                .prop('name', pplId)
      //                .prop('vaccine', vaccineCompleted)
                      

      // Put the task in the correct list
      //if (vaccineCompleted) {
        $('#peopleList').append($newTemplate)
      // } else {
      //   $('#unvaccinatedPeopleList').append($newTaskTemplate)
      // }

      // Show people record
      $newTemplate.show()
    }
  },

  createRecord: async () => {
    App.setLoading(true)
    const name = $('#newName').val()
    const vaccine = $('#newVaccine').val()
    await App.vaccine.createPeople(name, vaccine)
    window.location.reload()
  },

  // toggleCompleted: async (e) => {
  //   App.setLoading(true)
  //   const taskId = e.target.name
  //   await App.todoList.toggleCompleted(taskId)
  //   window.location.reload()
  // },

  setLoading: (boolean) => {
    App.loading = boolean
    const loader = $('#loader')
    const content = $('#content')
    //const vaccine = $('#vaccine')
    if (boolean) {
      loader.show()
      content.hide()
    } else {
      loader.hide()
      content.show()
      //vaccine.show()
    }
  }
}

$(() => {
  $(window).load(() => {
    App.load()
  })
})
