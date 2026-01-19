# word

## Goals

### Heads-Up Display

> Does this tool override default Vim keybindings?

No. The tool is designed to assist without ever getting in your way.

### Latency

> What's the latency goal for getting suggestions?

The goal is under 1.0 second.

[1.0 second is about the limit for the user's flow of thought to stay uninterrupted](https://www.nngroup.com/articles/response-times-3-important-limits/#:~:text=1.0%20second%20is%20about%20the%20limit%20for%20the%20user%27s%20flow%20of%20thought%20to%20stay%20uninterrupted).

### Cost

> What's the monthly cost goal?

The goal is under $1 a month. That's over 10x cheaper than [Grammarly Pro](https://www.grammarly.com/plans#:~:text=%2412,USD).

## Segmentation

> Does `word` use built-in Neovim sentence text objects?

No. `word` uses [`vim-textobj-sentence`](https://github.com/preservim/vim-textobj-sentence) to prevent abbreviations from being treated as sentence boundaries.

## Caching

> Does `word` cache the suggestions it generates?

Yes. The cache holds the two most recent suggestions for each sentence.

> Will suggestions for one sentence overwrite those for an identical sentence elsewhere?

No. `word` does not use the sentence's text as a cache key. Instead, it uses a Neovim extmark to pin suggestions to that specific instance of the sentence.

> Will I see my previous suggestions if I reopen a file?

No. The cache lives and dies with your editing session. Persistent caching would be unreliable if you:

- Edited the file outside of Neovim.

- Moved the file.

- Recovered from a swap file.
