(defn date-block [label date]
  (when date
    [:div.h6.nowrap.mr1
     [:span.mr1 label ": "]
     [:span.italic date]]))

(defn texts [language]
  (let [lang (keyword (or language "en"))]
    (get {:en {:sitemap "Sitemap"
               :created "Created"
               :updated "Last updated"
               :toc "Table of contents"
               :backlinks "Backlinks"}
          :fr {:sitemap "Plan de site"
               :created "Parution"
               :updated "Dernière mise à jour"
               :toc "Table des matières"
               :backlinks "Liens retour"}
          }
         lang)))

(defn default
  [{:keys [date-updated date-created meta
           build-url title render
           partials firn-article] :as data}]
  (let [{:keys [head nav footer]} partials
        {:keys [firn-article language]} (:keywords meta)
        txt (texts language)]
    [:html
     (head data)
     [:body
      (nav build-url)
      [:main.def-wrapper
       [:aside#sidebar.def-sidebar.unfocused
        [:h1 (:sitemap txt)]
        (render :sitemap {:sort-by :oldest})]
       [:article.def-content-wrap
        [:div.def-content
         #_(render :breadcrumbs)
         [:h1.mb0 title]
         [:div.flex
          (date-block (:created txt) date-created)
          (date-block (:updated txt) date-updated)]
         (if firn-article
           (render "Article" {:exclude-headline? true})
           (render :file))
         #_[:div.adjacent-files
            [:span (render :adjacent-files)]]
         (footer)]]

       (let [toc       (render :toc)
             backlinks (render :backlinks)]
         (when (or toc backlinks)
           [:aside#toc.def-toc.unfocused
            (when toc
              [:div
               [:span.h4 (:toc txt)]
               [:div (render :toc (when firn-article {:headline "Article", :exclude-headline? true}))]])
            (when backlinks
              [:div
               [:span.h4 (:backlinks txt)] backlinks])]))]]]))
