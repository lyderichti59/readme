(defn texts [language]
  (let [lang (keyword (or language "en"))]
    (get
     {:en
      {:privacy "Privacy"
       :privacy-policy "Privacy policy"
       :privacy-description "Only anonymized telemetry data are collected by default"
       :legals "Legals"
       :publishing "Publishing"
       :domiciliation "Domiciliation"
       :host "Host"
       :cookies "Cookies"
       :none "None"}
      :fr
      {:privacy "Vie privée"
       :privacy-policy "Politique de protection des données"
       :privacy-description "Seules des données de mesures d'audience sont récupérées par défaut."
       :legals "Mentions Légales"
       :publishing "Publication"
       :domiciliation "Domiciliation"
       :host "Hébergeur"
       :cookies "Cookies"
       :none "Aucun"}
      }
     lang)))

(defn footer [language]
  (let [txt (texts language)]
    [:footer#footer
     [:ul
      [:li "Copyright © 2021"]
      [:li "Lydéric Dutillieux"]
      [:li
       [:a {:href "mailto:lyderic.dutillieux@origenial.fr"}
        "lyderic.dutillieux@origenial.fr"]]
      [:li "+33651962453"]
      [:li
       [:details
        [:summary (:legals txt)]
        [:div
         [:table
          [:tr
           [:th {:scope "row"} (:publishing txt)]
           [:td "Lydéric Dutillieux"]]
          [:tr
           [:th {:scope "row"} (:domiciliation txt)]
           [:td "27 Rue Morand, 75011 Paris"]]
          [:tr
           [:th {:scope "row"} (:host txt)]
           [:td "OVH - +33972101007 - www.ovh.com/fr/"]]
          [:tr [:th {:scope "row"} (:cookies txt)] [:td (:none txt)]]]]]]
      [:li
       [:details
        [:summary (:privacy txt)]
        [:div
         [:span
          {:style
           "font-weight:600; font-size:1.5em; text-decoration:underline;"}
          (:privacy-policy txt)]
         [:p
          {:style "margin-bottom:0px;"}
          (:privacy-description txt)]
         [:iframe
          {:src
           (str "https://analytics.origenial.fr/index.php?module=CoreAdminHome&action=optOut&language=" language "&fontSize=0.8em&fontFamily=Robot,Ubuntu,sans-serif&fontColor=888888"),
           :style "border: 0; width: 100%; height:100px;font-color:var(--nav-bg-inverse);"}]]]]]
     [:script {:src "/static/js/main.js"}]]))
