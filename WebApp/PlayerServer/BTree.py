
from __future__ import (nested_scopes, generators, division, absolute_import, with_statement,
                        print_function, unicode_literals)


class BTree(object):
  """A BTree implementation with search and insert functions. Capable of any order t."""

  class Node(object):
    """A simple B-Tree Node."""
    abb = None

    def __init__(self, t):
      self.keys = []
      self.children = []
      self.leaf = True
      # t is the order of the parent B-Tree. Nodes need this value to define max size and splitting.
      self._t = t

    def split(self, parent, payload):
      """Split a node and reassign keys/children."""
      new_node = self.__class__(self._t)

      mid_point = self.size//2
      split_value = self.keys[mid_point]
      parent.add_key(split_value)

      # Add keys and children to appropriate nodes
      new_node.children = self.children[mid_point + 1:]
      self.children = self.children[:mid_point + 1]
      new_node.keys = self.keys[mid_point+1:]
      self.keys = self.keys[:mid_point]

      # If the new_node has children, set it as internal node
      if len(new_node.children) > 0:
        new_node.leaf = False

      parent.children = parent.add_child(new_node)
      if payload.nombre.lower().strip() < split_value.nombre.lower().strip():
        return self
      else:
        return new_node

    @property
    def _is_full(self):
      return self.size == 2 * self._t

    @property
    def size(self):
      return len(self.keys)

    def add_key(self, value):
      """Add a key to a node. The node will have room for the key by definition."""
      self.keys.append(value)
      new_value = self.keys[-1]
      self.keys.sort(key=lambda x: x.nombre.lower().strip(), reverse=False)
      return new_value
    def add_child(self, new_node):
      """
      Add a child to a node. This will sort the node's children, allowing for children
      to be ordered even after middle nodes are split.
      returns: an order list of child nodes
      """
      i = len(self.children) - 1
      while i >= 0 and self.children[i].keys[0].nombre.lower().strip() > new_node.keys[0].nombre.lower().strip():
        i -= 1
      return self.children[:i + 1]+ [new_node] + self.children[i + 1:]


  def __init__(self, t):
    """
    Create the B-tree. t is the order of the tree. Tree has no keys when created.
    This implementation allows duplicate key values, although that hasn't been checked
    strenuously.
    """
    self._t = t
    if self._t <= 1:
      raise ValueError("B-Tree must have a degree of 2 or more.")
    self.root = self.Node(t)

  def insert(self, payload):
    """Insert a new key of value payload into the B-Tree."""
    node = self.root
    # Root is handled explicitly since it requires creating 2 new nodes instead of the usual one.
    if node._is_full:
      new_root = self.Node(self._t)
      new_root.children.append(self.root)
      new_root.leaf = False
      # node is being set to the node containing the ranges we want for payload insertion.
      node = node.split(new_root, payload)
      self.root = new_root
    while not node.leaf:
      i = node.size - 1
      while i > 0 and payload.nombre.lower().strip() < node.keys[i].nombre.lower().strip() :
        i -= 1
      if payload.nombre.lower().strip() > node.keys[i].nombre.lower().strip():
        i += 1

      next = node.children[i]
      if next._is_full:
        node = next.split(node, payload)
      else:
        node = next
    # Since we split all full nodes on the way down, we can simply insert the payload in the leaf.
    return node.add_key(payload)

  def search(self, value, node=None):
    """Return True if the B-Tree contains a key that matches the value."""

    if node is None:
      node = self.root
    key = [x for x in node.keys if x.nombre == value.nombre]
    if key:
      return key[0]
    elif node.leaf:
      # If we are in a leaf, there is no more to check.
      return None
    else:
      i = 0
      while i < node.size and value.nombre.lower().strip() > node.keys[i].nombre.lower().strip():
        i += 1
      return self.search(value, node.children[i])

  def print_order(self):
    """Print an level-order representation."""
    this_level = [self.root]
    while this_level:
      next_level = []
      output = ""
      for node in this_level:
        if node.children:
          next_level.extend(node.children)
        output += str(node.keys) + " "
      print(output)
      this_level = next_level

  def show_arbol(self):
      this_level = [self.root]
      level = 0
      output = ""

      while this_level:
          next_level = []
          for index,node in enumerate(this_level):
              output += "level{0}node{1}[label=\"{2}|<node{3}pos{4}>\"];\n".format(level, index,'|'.join(['<node'+str(index)+'pos'+str(i)+'>|'+v.nombre for i, v in enumerate(node.keys)]),index,node.keys.__len__())
              if node.children:
                    next_level.extend(node.children)
                    output += "subgraph tranlevel{0}node{1}{{ \n\n".format(level, index)
                    for i,n in enumerate(node.children):
                        output +="level{0}node{1}:node{1}pos{2} ->  level{3}node{4}; \n".format(level,index,i,level +1,i)
                    output += "}"

          level = level + 1;
          this_level = next_level
      return "digraph G {{ node[shape=record];\n {0} }}".format(output)