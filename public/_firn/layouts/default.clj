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
        {:keys [firn-article language] :or {language  "en"}} (:keywords meta)
        txt (texts language)
        toc       (render :toc)
        backlinks (render :backlinks)]
    [:html
     (head data)
     [:body
      (nav build-url)
      [:main
       [:div.def-wrapper.border-bottom
        [:aside#sidebar.def-sidebar.unfocused
         [:span.h2.mt2 (:sitemap txt)]
         (render :sitemap {:sort-by :oldest})]
        [:article.rss
         {:class (str "def-content-wrap  border-left" (when (or toc backlinks) " border-right"))}
         [:div.def-content
          #_(render :breadcrumbs)
          [:h1.mb0 title]
          [:div.flex.flex-wrap
           (date-block (:created txt) date-created)
           (date-block (:updated txt) date-updated)]
          (if firn-article
            (render "Article" {:exclude-headline? true})
            (render :file))
          #_[:div.adjacent-files
             [:span (render :adjacent-files)]]]]
        (when (or toc backlinks)
          [:aside#toc.def-toc.unfocused
           (when toc
             [:div.mt2
              [:span.h4 (:toc txt)]
              [:div
               (render :toc (when firn-article
                              {:headline "Article", :exclude-headline? true}))]])
           (when backlinks
             [:div.mt2
              [:span.h4 (:backlinks txt)] backlinks])])]
       (footer language)]]]))
