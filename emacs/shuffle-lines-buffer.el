(defun shuffle-lines-buffer ()
  "Shuffle the lines of the current buffer."
  (interactive)
  (setq lines (line-number-at-pos (point-max)))
  (setq trs 0)
  (if (> lines 1)
      (while (<= trs lines)
        (setq trs (+ trs 1))
        (goto-char (point-min))
        (forward-line (1- trs))
        (beginning-of-line)
        (setq start1 (point))
        (setq start2 start1)
        (end-of-line)
        (setq end1 (point))
        (setq end2 end1)
        (while (or (and (<= start1 start2) (<= start2 end1))
                   (and (<= start1 end2)   (<= end2 end1))
                   (and (<= start2 start1) (<= start1 end2))
                   (and (<= start2 end1)   (<= end1 end2)))
          (goto-char (point-min))
          (forward-line (1- (random lines)))
          (beginning-of-line)
          (setq start2 (point))
          (end-of-line)
          (setq end2 (point)))
        (transpose-regions start1 end1 start2 end2)
        )))
