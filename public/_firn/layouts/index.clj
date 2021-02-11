(defn index
  [{:keys [render build-url partials site-url site-title] :as data}]
  (let [{:keys [head nav footer]} partials]
    [:html
     (head data)
     [:body
      [:div (nav build-url)]
      [:main.content {:style "max-width: 42em; padding: 32px; margin: 0 auto;"}
       [:div.py3
        (render "Details" :content)]]
      (footer "en")]]))
