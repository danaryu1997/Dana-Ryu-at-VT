module.exports = {
  networks: { // network key - specify network!
    development: {
      host: "127.0.0.1", //local host
      port: 7545, // port that gnache is runing on
      network_id: "*" // Match any network id
    }
  },
  solc: {
    optimizer: {
      enabled: true,
      runs: 200
    }
  }
}
