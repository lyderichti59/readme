(defn mobile-btn
  []
  [:div#nav-icon {:onClick "toggleMenu()"}
   [:span] [:span] [:span] [:span]])

(defn nav
  [build-url]
  (let [links [[(build-url "/") "Home"]
               #_[(build-url "/feed.xml") "RSS"]
               ["https://www.origenial.fr" "Origenial"]
               ["https://www.linkedin.com/in/lyderic-dutillieux/" "Linkedin"]
               ["https://www.twitter.com/lyderichti59/" "Twitter"]
               ["https://www.github.com/lyderichti59/readme" "Github"]]]
    ;; left nav.
    [:nav.nav
     [:div.nav-container
      [:div.nav-left
       (mobile-btn)]
      [:div.nav-links
       (for [l links]
         [:a.nav-links-item {:href (first l)}
          [:span (second l)]])]]]))
