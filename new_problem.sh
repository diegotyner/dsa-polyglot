#!/usr/bin/env bash
set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

if [ $# -ne 1 ]; then
	echo "Usage: $0 <topic>/<problem-dir>" >&2
	echo "Example: $0 intervals/0056-merge-intervals" >&2
	exit 1
fi

DIR="${1%/}"
TOPIC="${DIR%%/*}"

if [ "$TOPIC" = "linked-list" ]; then
	CPP_TEMPLATE="$SCRIPT_DIR/problem_template_linked_list.cpp"
	JAVA_TEMPLATE="$SCRIPT_DIR/problem_template_linked_list.java"
else
	CPP_TEMPLATE="$SCRIPT_DIR/problem_template.cpp"
	JAVA_TEMPLATE="$SCRIPT_DIR/problem_template.java"
fi

TARGET="$SCRIPT_DIR/$DIR"
mkdir -p "$TARGET"

copy_if_absent() {
	local src="$1" dest="$2"
	if [ -e "$dest" ]; then
		echo "Skipped $dest (already exists)"
	else
		cp "$src" "$dest"
		echo "Created $dest (from $(basename "$src"))"
	fi
}

copy_if_absent "$CPP_TEMPLATE" "$TARGET/solution.cpp"
copy_if_absent "$JAVA_TEMPLATE" "$TARGET/solution.java"
