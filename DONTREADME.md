# word

## Goals

### Heads-Up Display

> Does this tool override default Vim keybindings?

No. The tool is designed to assist without ever getting in your way.

### Tracking

> Can I see my review progress after closing and reopening a file?

Yes. It's designed to save your progress, so an accidental file close doesn't mean you have to start over.

### Latency

> What's the latency goal for getting suggestions?

The goal is under 1.0 second.

[1.0 second is about the limit for the user's flow of thought to stay uninterrupted](https://www.nngroup.com/articles/response-times-3-important-limits/#:~:text=1.0%20second%20is%20about%20the%20limit%20for%20the%20user%27s%20flow%20of%20thought%20to%20stay%20uninterrupted)

### Cost

> What's the monthly cost goal?

The goal is under $1 a month. That's over 10x cheaper than Grammarly Pro.

## Segmentation

> Does `word` use built-in Neovim sentence text objects?

No. `word` uses `vim-textobj-sentence` to prevent abbreviations from being treated as sentence boundaries.
