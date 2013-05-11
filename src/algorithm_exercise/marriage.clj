(ns algorithm-exercise.marriage)

(defn ^:private prefer? [prefs x y]
  (let [m (into {} (map-indexed #(vector %2 %1) prefs))]
    (< (m x) (m y))))

(defn ^:private shift-prefs [prefs who]
  (assoc prefs who (rest (prefs who))))

(defn solve [men's-prefs women's-prefs]
  (letfn
    [(solve' [[man & more :as men] men's-prefs engaged]
       (if (empty? men)
         engaged
         (let [woman (first (men's-prefs man))
               woman-pref (women's-prefs woman)]
           (if-let [suitor (engaged woman)]
             (if (prefer? woman-pref man suitor)
               (recur (cons suitor more)
                      (shift-prefs men's-prefs suitor)
                      (assoc engaged woman man))
               (recur men (shift-prefs men's-prefs man) engaged))
             (recur more men's-prefs (assoc engaged woman man))))))]
    (solve' (keys men's-prefs) men's-prefs {})))
