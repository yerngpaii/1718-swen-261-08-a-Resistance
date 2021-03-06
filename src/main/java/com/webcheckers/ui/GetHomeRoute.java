package com.webcheckers.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import com.webcheckers.appl.PlayerLobby;

import spark.*;

import static com.webcheckers.ui.InterfaceVariable.*;

/**
 * The UI Controller to GET the Home page.
 *
 * @author <a href='mailto:bdbvse@rit.edu'>Bryan Basham</a>
 * @author Anorwen - - - edc8230@rit.edu
 */
public class GetHomeRoute implements Route {

  // ----------
  // Attributes
  // ----------

  private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());
  private PlayerLobby playerLobby;
  private final TemplateEngine templateEngine;

  // ------------
  // Constructors
  // ------------

  /**
   * Create the Spark Route (UI controller) for the
   * {@code GET /} HTTP request.
   *
   * @param templateEngine
   *   the HTML template rendering engine
   */
  public GetHomeRoute(final TemplateEngine templateEngine, PlayerLobby playerlobby) {
    // validation
    Objects.requireNonNull(templateEngine, "templateEngine must not be null");

    this.templateEngine = templateEngine;
    this.playerLobby = playerlobby;

    LOG.config("GetHomeRoute is initialized.");
  }

  // -------
  // Methods
  // -------

  private static String removeSpaces(String string) {
    return string.replaceAll(" ", "\"");
  }

  public static String returnSpaces(String string) {
    return string.replaceAll("\"", " ");
  }

  /**
   * Creates a list of links to start games with the corresponding players. May be used in
   * any class that needs to render the home page with game links.
   * @param viewingUser String username of the player not to be shown on the list.
   * @param playerlobby Playerlobby of the application.
   * @return String of <li><a href="/game>'username</a></li> Kleene Star
   */
  public static String addPlayersList(String viewingUser, PlayerLobby playerlobby) {
    ArrayList<String> list = playerlobby.getUsers();

    if(list.contains(viewingUser)) { // The Current User Can't Play a Game With Their Self
      list.remove(viewingUser);
    }

    String result = "";
    for(String user: list) {
      result += "<li><a href=/game?opponent=" + removeSpaces(user) + "&summoner=" + removeSpaces(viewingUser)+">" + user + "</a></li>";
    }
    return "<ul>" + result + "</ul>"; // Unordered List Label
  }

  /**
   * Creates a String representing the total number of users online, including the current user if they are signed in.
   * May be used by and other class handle() that needs to render the home page.
   * @param playerlobby PlayerLobby of the application
   * @return String version of an Integer.
   */
  public static String showNumber(PlayerLobby playerlobby) {
    return Integer.toString(playerlobby.getUsers().size());
  }

  /**
   * Render the WebCheckers Home page.
   *
   * @param request
   *   the HTTP request
   * @param response
   *   the HTTP response
   *
   * @return
   *   the rendered HTML for the Home page
   */
  @Override
  public Object handle(Request request, Response response) {
    LOG.finer("GetHomeRoute is invoked.");

    Map<String, Object> vm = new HashMap<>();
    final Session httpSession = request.session();

    if(this.playerLobby.getUser(httpSession) == null) { // Yet to Sign In
      vm.put(USERNAME, "");
      vm.put(SIGN, "<a href=/SignIn>Sign In</a>");
      vm.put(SHOW_PLAYERS, "<p>Please Sign In to see players.</p>");
    }
    else { // Already Signed In
      if(httpSession.attribute("inGame")) { // Queued for a Game
        response.redirect("/game");
      }

      vm.put(USERNAME, this.playerLobby.getUser(httpSession).toString());
      vm.put(SIGN, "<a href=/SignedOut>Sign Out</a>");
      vm.put(SHOW_PLAYERS, addPlayersList(playerLobby.getUser(httpSession).toString(), playerLobby));
    }

    vm.put(HOME_MESSAGE, "");
    vm.put(NUMBER_USERS, showNumber(playerLobby));

    vm.put(TITLE, "Welcome!");
    return templateEngine.render(new ModelAndView(vm , HOME_NAME));
  }
}
