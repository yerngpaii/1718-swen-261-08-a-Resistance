<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
    <meta http-equiv="refresh" content="10">
    <title>${title} | Web Checkers</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
  <div class="page">

    <h1>Web Checkers</h1>
    
    <div class="navigation">
      <a href="/">My Home</a>
      ${sign}
      <info>${username}</info>
    </div>

    <div class="body">
      <p>Welcome to the world of online Checkers. There are ${numberUsers} players online!</p>

      ${homeMessage}

      <h2>Players Online:</h2>
      <div class = "footer">
        ${showPlayers}
      </footer>
    </div>
  </div>
</body>
</html>