(defun number-lines-buffer ()
  "Number the lines of the current buffer in ascending order."
  (interactive)
  (setq mybuffer (current-buffer))
  (setq x 0)
  (setq c 0)
  (goto-char (point-min))
  (while (= c 0)
    (setq x (+ 1 x))
    (princ x mybuffer)
    (princ " "  mybuffer)
    (setq c (forward-line 1))))

